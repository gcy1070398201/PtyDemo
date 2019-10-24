package com.example.pty.mapper;

import com.example.pty.mode.Publish;

/**
 * PublishMapper 扩展mapper
 */
public interface PublishExtMapper {
    /**
     * 页面游览次数
     * @param record
     * @return
     */
    int incView(Publish record);
    /**
     * 信息回复次数
     * @param record
     * @return
     */
    int incComment(Publish record);
}