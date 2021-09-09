package com.cbums.core.tag.Service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.core.tag.domain.Tag;
import com.cbums.core.tag.domain.TagRepository;
import com.cbums.core.tag.dto.TagApplyRequest;
import com.cbums.core.tag.dto.TagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Long createTag(TagRequest req) {
        return tagRepository.save(Tag.builder()
                .tagContent(req.getTagContent())
                .build()).getTagId();
    }

    public List<Tag> getTagsById(TagApplyRequest req) {
        List<Tag> tags = new ArrayList<>();
        for(Long id : req.getTagList()) {
            tags.add(findById(id));
        }

        return tags;
    }

    private Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }





}
