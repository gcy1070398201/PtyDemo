package com.example.pty.service;

import com.example.pty.dto.PubLishListDto;
import com.example.pty.dto.PublishDto;
import com.example.pty.mapper.PublishMapper;
import com.example.pty.mode.Publish;
import com.example.pty.mode.PublishExample;
import com.example.pty.mode.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishService {

    @Autowired
    PublishMapper publishMapper;

    @Autowired
    UserService userService;

    public PubLishListDto select(Integer page, Integer size) {
        if (page<=0)page=1;
        Integer currentPage = 0;
        Integer totalPage=0;
        PubLishListDto pubLishListDto = new PubLishListDto();
        Integer totalCount = (int)publishMapper.countByExample(new PublishExample());
        //计算总页数
        if (totalCount % size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        currentPage = (page-1) * size;

        if (page<=0){
            page=1;
        }else if (page>totalPage){
            page=totalPage;
        }



        pubLishListDto.setPageInfo(totalPage, page, size);

        List<PublishDto> publishDtos = new ArrayList<>();

        List<Publish> publishModes = publishMapper.selectByExampleWithRowbounds(
                new PublishExample(), new RowBounds(currentPage,size));

        if (publishModes != null && publishModes.size() > 0) {
            for (Publish publishMode : publishModes) {
                PublishDto publishDto = new PublishDto();
                BeanUtils.copyProperties(publishMode, publishDto);
                User user = userService.findUserId(publishDto.getCreatId());
                publishDto.setUser(user);
                publishDtos.add(publishDto);
            }
        }
        pubLishListDto.setPublishDtos(publishDtos);
        return pubLishListDto;
    }
}
