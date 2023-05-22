package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.exception.BusinessException;
import com.example.mapper.LogisticsMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ResponseBody
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private LogisticsMapper logisticsMapper;

    @PostMapping("/upload")
    @ApiOperation(tags = "IgnoreProductId 页面操作", value = "ProductId数据文件上传")
    public String uploadProductId(MultipartFile file) throws Exception {

        if (ObjectUtil.isNull(file)) {
            throw new BusinessException("请检查Excel是否有数据");
        }

        // 文件名的获取 并判断是否是excel
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new BusinessException("上传的文件名为空");
        }
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (!"xls".equals(fileSuffix) && !"xlsx".equals(fileSuffix)) {
            throw new BusinessException("文件格式不正确,请检查后重新上传!");
        }
}
