package sdhs.rereadserver.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RentService {
    private final RentRepository rentRepository;
    public boolean saveRent(Rent rent){
        rentRepository.save(rent);
        return true;
    }
}
