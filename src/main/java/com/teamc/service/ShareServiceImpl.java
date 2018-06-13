package com.teamc.service;


import com.teamc.model.Share;
import com.teamc.repository.ShareRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@NoArgsConstructor
public class ShareServiceImpl implements ShareService {

    private ShareRepository shareRepository;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public Share findByName(String name) {
        return shareRepository.findByName(name);
    }

    @Override
    public void save(Share share) {
        shareRepository.save(share);
    }

    @Override
    public void update(String name, Share share) {
        Share shareDb = shareRepository.findByName(name);
        if (shareDb != null) {
            shareDb.setPrice(share.getPrice());
            shareRepository.save(shareDb);
        } else {
            shareRepository.save(share);
        }
    }

    @Override
    public void delete(String name) {
        Share share = shareRepository.findByName(name);
        shareRepository.delete(share);
    }

    @Override
    public void shareAvailable(Share share, double available) {
        share.setAvailable(available);
        shareRepository.save(share);
    }

    @Override
    public List<Share> threeRandomShare(){
        return shareRepository.threeRandomShare();
    };
}
