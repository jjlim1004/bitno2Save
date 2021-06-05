package com.example.demo.controller.chat;


import com.example.demo.domain.chat.RoomVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class chatController {

    //DB에 저장하는게 아니라서 방의 정보를 담아둘 List<Room> 컬렉션을 생성
    List<RoomVO> roomList = new ArrayList<RoomVO>();

    int roomNo = 0;

    @RequestMapping("chat")
    public String chat(){

        return "chat/chat";
    }

    @RequestMapping("room")
    public String room(){

        return "chat/room";
    }

//방을 생성
    @RequestMapping("createRoom")
    @ResponseBody
    public List<RoomVO> createRoom(@RequestParam HashMap<Object, Object> params){
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")){
            RoomVO room = new RoomVO();
            room.setRoomNo(++roomNo);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;

    }

//방을 가져옴
    @RequestMapping("getRoom")
    @ResponseBody
    public List<RoomVO> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    @RequestMapping("moveChating")
    public String chating(@RequestParam HashMap<Object, Object> params, ModelAndView model){
        int roomNo = Integer.parseInt((String) params.get("roomNo"));

        List<RoomVO> newList = roomList.stream().filter(o->o.getRoomNo() == roomNo).collect(Collectors.toList());
        if(newList != null && newList.size() >0){
            model.addObject("roomName", params.get("roomName"));
            model.addObject("roomNo", params.get("roomNumber"));
            return "chat/chat";
        }else{
            return "chat/room";
        }
    }


}
