package sdhs.rereadserver.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentService {
    private final RentRepository rentRepository;
    public Optional<Rent> findByBookNo(String no){
        return rentRepository.findByBookNo(no);
    }
    public Optional<Rent> findById(Long id){return rentRepository.findById(id);}
    public boolean saveRent(Rent rent){
        rentRepository.save(rent);
        return true;
    }
}
