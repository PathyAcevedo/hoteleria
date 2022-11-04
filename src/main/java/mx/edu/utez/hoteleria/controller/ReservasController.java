package mx.edu.utez.hoteleria.controller;
@Controller
@RequestMapping("/reservas")
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
        return "reservas/listRoom";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable() long id,Model model,  RedirectAttributes redirectAttributes){
        Rooms room = roomService.findById(id);
        if (!room.equals(null)){
            model.addAttribute("room", room);
            return "reservas/detailRoom";
        }else {
            redirectAttributes.addFlashAttribute("msg_error", "No se encontró la habitación solicitada");
            return "redirect:/reservas/listRoom";
        }
    }

    @GetMapping(path = "/filterList")
    public String reservasFil(Model model) {
        List<Rooms> roomList = roomService.findAll();
        model.addAttribute("roomList", roomList);
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("jobs", jobService.listAll());
        return "reservas/listRoom";
    }

    @PostMapping(path = "/filter")
    public String reservaFiltro(@RequestParam("category") long idCategory, @RequestParam("size") long idSize, Model model) {
        Category category = categoryService.show(idCategory});
        Size size = sizeService.show(idSize);
        List<Rooms> roomList = roomService.buscarporCategoriaoTamanio(category, size);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("sizes", sizeService.listAll());
        model.addAttribute("roomList", roomList);
        return "reservas/listRoom";
    }



}