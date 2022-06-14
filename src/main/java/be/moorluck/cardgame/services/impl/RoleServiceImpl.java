package be.moorluck.cardgame.services.impl;

import be.moorluck.cardgame.mappers.RoleMapper;
import be.moorluck.cardgame.models.dtos.RoleDTO;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.models.forms.RoleForm;
import be.moorluck.cardgame.repository.RoleRepository;
import be.moorluck.cardgame.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleDTO getById(Long id) {
        return roleMapper.toDto(roleRepository.findById(id).orElse(null));
    }

    @Override
    public Set<RoleDTO> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDTO insert(RoleForm form) {
        return roleMapper.toDto(roleRepository.save(
                roleMapper.toEntity(form)
        ));
    }

    @Override
    public RoleDTO delete(Long id) {
        RoleDTO dto = roleMapper.toDto(roleRepository.findById(id).orElse(null));

        if (dto == null)
            return null;

        roleRepository.deleteById(id);

        return dto;
    }
}
