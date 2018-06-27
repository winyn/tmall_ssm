package com.winn.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 19:29 2018/6/13
 * @ModifiedBy:
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
