package mx.edu.utez.hoteleria.controller;


import mx.edu.utez.hoteleria.entity.Roles;
import mx.edu.utez.hoteleria.entity.Rooms;
import mx.edu.utez.hoteleria.entity.Users;
import mx.edu.utez.hoteleria.security.BlacklistController;
import mx.edu.utez.hoteleria.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomsService roomService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SizeService sizeService;

    @GetMapping(value = "/list")
    public String findAll(Model model, Pageable pageable) {
        Page<Rooms> listRooms = roomService
                .listarPaginacion(PageRequest.of(pageable.getPageNumber(), 8, Sort.by("name").ascending()));
        model.addAttribute("listRooms", listRooms);
        return "rooms/listRoom";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable() long id,Model model,  RedirectAttributes redirectAttributes){
        Rooms room = roomService.findById(id);
        if (!room.equals(null)){
            model.addAttribute("room", room);
            return "rooms/detailRoom";
        }else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la habitación solicitada");
            return "redirect:/rooms/listRoom";
        }
    }


    @GetMapping("/create")
    public String crear(Rooms room, Model modelo) {
        modelo.addAttribute("listCategories", categoryService.findAll());
        modelo.addAttribute("listSizes", sizeService.findAll());

        //modelo.addAttribute("facility", facility);
        //modelo.addAttribute("listFacilities", facilityService.findByName(facility.name)));

        return "rooms/createRoom";
    }

    @GetMapping("/disable/{id}")
    public String disableRoom(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Rooms tmp = roomService.findById(id);
            if (tmp.getEnabled() == 1) {
                tmp.setEnabled(0);
                redirectAttributes.addFlashAttribute("msg_success", "Habitacion deshabilitada");
            } else {
                tmp.setEnabled(1);
                redirectAttributes.addFlashAttribute("msg_success", "Habitacion habilitada");
            }

        roomService.save(tmp);
        return "redirect:/rooms/list";
    }


    @PostMapping(value = "/save")
    public String save( Rooms room,RedirectAttributes redirectAttributes) {
        String msgOk = "";
        String msgError = "";

        room.setEnabled(1);
        if(room.getId() != null){
            msgOk = "Habitacion Actualizada correctamente";
            msgError = "La habitacion NO pudo ser Actualizada correctamente";
        }else{

            msgOk = "Habitacion Guardado correctamente";
            msgError = "La  habitacion NO pudo ser guardada correctamente";
        }

        boolean res = roomService.save(room);
        if (res) {
            redirectAttributes.addFlashAttribute("msg_success", msgOk);
            return "redirect:/rooms/list";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", msgError);
            return "redirect:/rooms/create";
        }
    }


    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable long id, Model modelo, RedirectAttributes redirectAttributes) {
        Rooms room = roomService.findById(id);
        if (room != null) {
            modelo.addAttribute("room", room);
            modelo.addAttribute("listSCategories", categoryService.findAll());
            modelo.addAttribute("listSizes", sizeService.findAll());

            return "rooms/createRoom";
        }else{
            return "rooms/listRoom";
        }
    }



    @DeleteMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Rooms room = roomService.findById(id);
        if (!room.equals(null)) {
            boolean res = roomService.delete(id);
            if (res) {
                redirectAttributes.addFlashAttribute("msg_success", "Habitacion eliminado correctamente");
                return "rooms/detailRoom";
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "No se pudo eliminar la habitacion");
                return "rooms/detailRoom";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la habitacion solicitada");
            return "rooms/detailRoom";
        }
    }
}

