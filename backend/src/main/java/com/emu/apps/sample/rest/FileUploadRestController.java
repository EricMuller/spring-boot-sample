package com.emu.apps.sample.rest;

import com.emu.apps.sample.model.Question;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by eric on 07/06/2017.
 */
@RestController
@RequestMapping("api/upload")
@Api(value="upload-store", description="Upload a data file",tags = "File Upload")
public class FileUploadRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "upload a question json file", response = ResponseEntity.class)
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadQuestionFile(@RequestParam("file") MultipartFile file) {

        try {

            logger.info(file.getName());
/*
 MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        Iterator<String> it=multipartRequest.getFileNames();
        MultipartFile multipart=multipartRequest.getFile(it.next());
        String fileName=id+".png";
        String imageName = fileName;

        byte[] bytes=multipart.getBytes();
        BufferedOutputStream stream= new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/"+fileName));;

        stream.write(bytes);
        stream.close();
        return new ResponseEntity("upload success", HttpStatus.OK);

 */
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
