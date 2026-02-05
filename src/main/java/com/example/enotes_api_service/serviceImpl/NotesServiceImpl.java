package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.entity.FileDetails;
import com.example.enotes_api_service.entity.Notes;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.repo.CategoryRepository;
import com.example.enotes_api_service.repo.FileRepository;
import com.example.enotes_api_service.repo.NotesRepository;
import com.example.enotes_api_service.service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public Boolean saveNotes(String notes, MultipartFile file) throws Exception {

        //converting string to json
        ObjectMapper ob = new ObjectMapper();
        NotesDTO notesDTO = ob.readValue(notes, NotesDTO.class);

        FileDetails fileDetails = saveFileDetails(file);

        //checking valiadtion of category
        checkCategoryExists(notesDTO.getCategory());

        Notes notesMap = mapper.map(notesDTO, Notes.class);

//FileDetails fileDtls = saveFileDetails(file);
        if (!ObjectUtils.isEmpty(fileDetails)) {
            notesMap.setFileDetails(fileDetails);

        } else {
            notesMap.setFileDetails(null);
        }

        Notes saveNotes = notesRepository.save(notesMap);

        if (!ObjectUtils.isEmpty(saveNotes)) {
            return true;
        } else {
            return false;
        }


    }


    private FileDetails saveFileDetails(MultipartFile file) throws IOException {
        if (!ObjectUtils.isEmpty(file) && !file.isEmpty()) {
            FileDetails fileDetails = new FileDetails();
            String originalFileName = file.getOriginalFilename();
            fileDetails.setOriginalFileName(originalFileName);
            fileDetails.setDisplayFileName(getDisplayName(originalFileName));

            //generate any random string
            String rndString = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalFileName);
            //allowing only specific type of file to database
            List<String> extensionAllowed = Arrays.asList("pdf","xlsx","jpg","docx");

            if(!extensionAllowed.contains(extension) ){
                throw new IOException("invalid file format allow only pdf, xlsx, jpg,docx");

            }

            String uploadFileName = rndString + "." + extension;
            fileDetails.setUploadedFileName(uploadFileName);
            fileDetails.setFileSize(file.getSize());


            File saveFile = new File(uploadPath);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            //path
            String storePath = uploadPath.concat(uploadFileName);
            fileDetails.setPath(storePath);

            //upload file java NIO way
            Long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
            if (upload != 0) {
                FileDetails saveFileDetails = fileRepository.save(fileDetails);

                return saveFileDetails;
            }

        } else {
            return null;
        }
        return null;
    }

    private String getDisplayName(String origunalFileName) {

        String extension = FilenameUtils.getExtension(origunalFileName);
        String fileName = FilenameUtils.removeExtension(origunalFileName);

        if (fileName.length() > 8) {
            fileName = fileName.substring(0, 7);


        }
        fileName = fileName + "." + extension;
        return fileName;
    }

    private void checkCategoryExists(NotesDTO.CategoryDTO categoryDTO) throws Exception {
        categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("category id is Invalid"));
    }

    @Override
    public List<NotesDTO> getAllNotes() {
        return notesRepository
                .findAll()
                .stream()
                .map(note -> mapper.map(note, NotesDTO.class))
                .collect(Collectors.toList());
    }
}
