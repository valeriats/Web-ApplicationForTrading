package com.teamc.service;

import com.teamc.model.Share;

import java.util.List;


public interface ShareService {

    List<Share> findAll();

    Share findByName(String name);

    void save(Share share);

    void update(String name, Share share);

    void delete(String name);

    void shareAvailable(Share share, double available);

    List<Share> threeRandomShare();
}
