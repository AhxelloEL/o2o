package com.al.o2o.dto;

import java.io.InputStream;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.dto
 * @ClassName:ImageHolder
 * @Description 图片信息封装
 * @date2021/7/12 13:36
 */
public class ImageHolder {

	private String imageName;
	private InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

}
