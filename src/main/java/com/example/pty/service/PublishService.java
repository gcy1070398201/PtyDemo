package com.example.pty.service;

import com.example.pty.dto.PubLishListDto;
import com.example.pty.dto.PublishDto;
import com.example.pty.mapper.PublishMapper;
import com.example.pty.mapper.PublishExtMapper;
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
    @Autowired
    PublishExtMapper publishExtMapper;

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

    /**
     * 更新或者插入数据
     * @param publishMode
     */
    public void createOrUpdate(Publish publishMode) {

        if (publishMode.getId()==null){
            //新增数据
            publishMode.setGmtCreate(System.currentTimeMillis());
            publishMode.setGmtModified(publishMode.getGmtCreate());
            publishMode.setViewCount(0);
            publishMode.setReplyCount(0);
            publishMapper.insert(publishMode);
        }else{
            //更新数据
            Publish publishDb = publishMapper.selectByPrimaryKey(publishMode.getId());
            if (publishDb==null){
               //                不存在
                return;
            }
            if (publishDb.getCreatId().longValue()!=publishMode.getCreatId().longValue()){
                //                不存在
                return;
            }
            Publish updateQuestion=new Publish();
            updateQuestion.setTitle(publishMode.getTitle());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setDescribeText(publishMode.getDescribeText());
            updateQuestion.setLabel(publishMode.getLabel());
            updateQuestion.setGmtModified(updateQuestion.getGmtCreate());
            PublishExample publishExample = new PublishExample();
            publishExample.createCriteria().andIdEqualTo(publishMode.getId());
            int i = publishMapper.updateByExampleSelective(updateQuestion, publishExample);
            if (i!=1){
                //更新失败
            }
        }

    }

    /**
     * 根据ID 获取信息
     * @param id
     * @return
     */
    public Publish selectById(Long id) {
        Publish publish = publishMapper.selectByPrimaryKey(id);
        if (publish==null){
            //信息不存在
        }
        return publish;
    }

    /**
     * 增加游览次数
     * @param id
     */
    public void incView(Long id) {
        Publish publishMode = new Publish();
        publishMode.setId(id);
        publishMode.setViewCount(1);
        publishExtMapper.incView(publishMode);
    }

    /**
     * 获取列表信息 和用户信息 并组装
     * @param id
     * @return
     */
    public PublishDto getById(Long id) {
        Publish publish = publishMapper.selectByPrimaryKey(id);
        if (publish == null) {
          //不为空
        }
        PublishDto publishDto=new PublishDto();
        BeanUtils.copyProperties(publish, publishDto);
        User userdb = userService.findUserId(publish.getCreatId());
        publishDto.setUser(userdb);
        return publishDto;
    }
}
