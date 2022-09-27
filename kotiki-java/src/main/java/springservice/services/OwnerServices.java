package springservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springservice.dto.OwnerRequest;
import springservice.entity.CatsEntity;
import springservice.entity.OwnersEntity;
import springservice.repository.CatRepository;
import springservice.repository.OwnerRepository;
import springservice.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OwnerServices implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CatRepository catRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails owner = ownerRepository.findByUsername(username);

        if (owner == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return owner;
    }


    public OwnersEntity addOwner(OwnerRequest ownerRequest) throws Exception {
        if (ownerRepository.findByUsername(ownerRequest.getUsername()) != null)
            throw new Exception("username also exist");
        OwnersEntity owner = new OwnersEntity();
        owner.setName(ownerRequest.getName());
        owner.setDatebirth(ownerRequest.getDateBirth());
        owner.setUsername(ownerRequest.getUsername());
        owner.setPassword(ownerRequest.getPassword());
        owner.getRolesOwner().add(roleRepository.findByName("ROLE_USER"));
        ownerRepository.save(owner);
        return owner;
    }

    public OwnersEntity getOwner(Integer id) {
        return ownerRepository.getById(id);
    }

    public OwnersEntity findByUserName(String username) {
        return ownerRepository.findByUsername(username);
    }


    public void deleteOwner(Integer id) {
        OwnersEntity owner = getOwner(id);
        ownerRepository.delete(owner);
    }


    public void addOwnerOfCat(Integer catId, Integer ownerId) {
        CatsEntity cat = catRepository.getById(catId);
        OwnersEntity owner = ownerRepository.getById(ownerId);
        ownerRepository.addOwner(cat, owner);
        ownerRepository.save(owner);
    }

    public List<CatsEntity> getAllCatsOfOwner(Integer ownerId) {
        return getOwner(ownerId).getOwnersCats();
    }

    //TODO JPA
    public void addAdmin(Integer id) {
        OwnersEntity owner = ownerRepository.getById(id);
        if (owner.getRolesOwner().size() < 2)
            owner.getRolesOwner().add(roleRepository.findByName("ROLE_ADMIN"));
        ownerRepository.save(owner);
    }

    public List<CatsEntity> getByColorId(Integer colorId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OwnersEntity owner = findByUserName(authentication.getName());
        if (owner.getRolesOwner().size() == 2)
            return catRepository.findByColorid(colorId);
        else {
            return owner.getOwnersCats().stream().filter(cats -> Objects.equals(cats.getColorid(), colorId)).collect(Collectors.toList());
        }
    }

    public List<Integer> getCatFriendsId(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OwnersEntity owner = findByUserName(authentication.getName());
        ArrayList<Integer> friends = new ArrayList<>();
        if (owner.getRolesOwner().size() == 2 || owner.getOwnersCats().stream().anyMatch(cat -> Objects.equals(cat.getId(), id))) {
            CatsEntity cat = catRepository.getById(id);
            for (CatsEntity i : cat.getFriends()) {
                friends.add(i.getId());
            }
            for (CatsEntity i : cat.getFriendsOf()) {
                friends.add(i.getId());
            }
        }
        return friends;
    }

}
