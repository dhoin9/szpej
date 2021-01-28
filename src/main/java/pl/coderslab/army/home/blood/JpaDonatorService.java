package pl.coderslab.army.home.blood;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class JpaDonatorService implements DonatorService {
    private final DonatorRepository donatorRepository;

    public JpaDonatorService(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public void add(Donator donator) {
    donatorRepository.save(donator);
    }

    @Override
    public void delete(Long id) {
    donatorRepository.deleteById(id);
    }

    @Override
    public void update(Donator donator) {
        donatorRepository.save(donator);
    }


    @Override
    public Donator get(Long id) {
        return donatorRepository.getById(id);
    }

    @Override
    public List<Donator> donorByUnit(Long id) {
        List<Donator> donatorList = new ArrayList<>();
        List<String> donatorsId =donatorRepository.allDonorsByUnit(id);
        for(String donorId: donatorsId){
            Donator donator = get(Long.parseLong(donorId));
            donatorList.add(donator);
        }
        System.out.println("tabel rozmiar: "+donatorList.size());
        return donatorList;
    }

    @Override
    public List<Donator> getAll() {
        return  donatorRepository.findAll();
    }
}
