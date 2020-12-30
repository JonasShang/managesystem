package com.ruoyi.common.core.domain.entity;

/**
 * <p>Description: </p>
 * 电子印章实体类
 * @author lchdevwangs
 * @date 2020/7/21
 */
public class EleSealDO {
    /**
     * 本地文件存储路径
     */
    private String filePath;
    /**
     * 盖章后的文件在本地的存储路径
     */
    private String localStorePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLocalStorePath() {
        return localStorePath;
    }

    public void setLocalStorePath(String localStorePath) {
        this.localStorePath = localStorePath;
    }

    @Override
    public String toString() {
        return "EleSealDO{" +
                "filePath='" + filePath + '\'' +
                ", localStorePath='" + localStorePath + '\'' +
                '}';
    }
}
