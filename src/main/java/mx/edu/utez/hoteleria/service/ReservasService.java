package mx.edu.utez.hoteleria.service;


@Service
public class ReservasService {

    @Autowired
    private IReservasRepository reservasRepository;

    public List<Rooms> findAll() {
        return reservasRepository.findAll();
    }

    public Rooms findById(long id) {
        return reservasRepository.findById(id);
    }


    List<Rooms> buscarporCategoriaoTamanio(Category category, Size size);
}
