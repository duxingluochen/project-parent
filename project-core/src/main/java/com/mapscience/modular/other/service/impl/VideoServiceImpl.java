package com.mapscience.modular.other.service.impl;

import com.mapscience.modular.other.model.Video;
import com.mapscience.modular.other.mapper.VideoMapper;
import com.mapscience.modular.other.service.IVideoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-12-29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
