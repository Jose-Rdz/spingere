package com.spingere.service;

import java.util.List;
import com.spingere.dto.UserDto;
import com.spingere.model.Usuario;
import com.spingere.utils.SpingereException;

/**
 *
 * @author G13380
 */
public interface UserService {

    /**
     * 
     * @return
     * @throws SpingereException 
     */
    List<Usuario> getUsers() throws SpingereException;
    
    /**
     * 
     * @param dto
     * @throws SpingereException 
     */
    void saveUser(UserDto dto) throws SpingereException;
    
}
