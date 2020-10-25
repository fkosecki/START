package io.github.mat3e.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class LangService {
    private LangRepository repository;

    public LangService(){
        this(new LangRepository());
    }

    public LangService(LangRepository repository){
        this.repository = repository;
    }

//    List<LangDTO> findAll(){
//        List<LangDTO> langsDTO = new ArrayList<>();
//        for (Lang l: repository.findAll()) {
//            langsDTO.add(new LangDTO(l));
//        }
//        return langsDTO;
//    }

    List<LangDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }
}
