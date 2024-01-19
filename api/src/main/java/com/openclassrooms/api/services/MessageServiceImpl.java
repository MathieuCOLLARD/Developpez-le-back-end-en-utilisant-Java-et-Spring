package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.MessageDTO;
import com.openclassrooms.api.entity.MessageEntity;
import com.openclassrooms.api.repository.MessageRepository;
import com.openclassrooms.api.repository.RentalRepository;
import com.openclassrooms.api.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Data
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param messageDTO
     * @return String
     */
    @Override
    public String createMessage(MessageDTO messageDTO) {
        MessageEntity message = modelMapper.map(messageDTO, MessageEntity.class);
        message.setRental_id(rentalRepository.findById(messageDTO.getRental_id()).get().getId());
        message.setUser_id(userRepository.findById(messageDTO.getUser_id()).get().getId());
        message.setCreated_at(new Timestamp(System.currentTimeMillis()));
        message.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(message);
        return "Message send with success";
    }
}
