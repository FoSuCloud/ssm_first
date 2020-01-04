package util;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 
/**
 * 文件上传工具类
 * @author admin
 *
 */
 
public class upload {
 
	
	/**
	 * 
	 * @param originalFilename
	 * @return
	 */
    public  String getFileName(String originalFilename){
        String fileName=String.valueOf(System.currentTimeMillis()) + "." + originalFilename;
        return fileName;
    }
    
    /**
     * 返回文件名
     * @param file
     * @return
     */
    public String getImgPath(MultipartFile file) {
    		System.out.println("file:" + file);
            if("".equals(file.getOriginalFilename())){
               return null;
            }
            //获取上传图片的文件名,变为时间+图片名
            String fileName = getFileName(file.getOriginalFilename());
            System.out.println("filename:" + fileName);
//            Constant.PICTURE_PATH
            String filePath = "D:\\ssm_imgs\\"  + fileName;
            //创建文件对象
            File tagetFile = new File("D:\\ssm_imgs\\" + fileName);
            //文件名不存在 则新建文件，并将文件复制到新建文件中
            if (!tagetFile.exists()) {
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    //保存图片
                    file.transferTo(tagetFile);
                    return fileName;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
    }
}

