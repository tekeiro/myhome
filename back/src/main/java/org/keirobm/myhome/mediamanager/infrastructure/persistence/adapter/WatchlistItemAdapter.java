package org.keirobm.myhome.mediamanager.infrastructure.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItem;
import org.keirobm.myhome.mediamanager.domain.search.port.WatchlistItemPort;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper.WatchlistItemMapper;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.WatchlistItemNodeRepository;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.WatchlistItemRepository;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WatchlistItemAdapter implements WatchlistItemPort {

    private final WatchlistItemRepository itemsRepository;

    private final WatchlistItemNodeRepository nodesRepository;

    private final WatchlistItemMapper mapper;
    
    @Override
    public PageResult<WatchlistItem> getAll(PageRequest pageRequest) {
        final var itemsDb = itemsRepository.findAll(pageRequest.toPageable());
        final List<WatchlistItem> results = new ArrayList<>();
        itemsDb.getContent().stream().forEach(item -> {
            final var itemResult = this.mapper.toDomain(item);
            results.add(itemResult);
            final var nodes = this.nodesRepository.findByParentId(item.getId())
                .stream().map(this.mapper::nodeToDomain).toList();
            itemResult.setNodes(nodes);
        });
        return PageResult.of(results, itemsDb);
    }

}
