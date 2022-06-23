package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.block.SimpleBlockHashDto;
import ssafy.nfticket.dto.block.SimpleBlockHashTicketDto;
import ssafy.nfticket.entity.BlockHash;
import ssafy.nfticket.repository.BlockHashRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlockService {

    private final BlockHashRepository blockHashRepository;

    @Transactional
    public String addBlockHash(SimpleBlockHashTicketDto simpleBlockHashTicketDto) {
        BlockHash newBlockHash = new BlockHash();
        newBlockHash.setTicketId(simpleBlockHashTicketDto.getTicketId());
        newBlockHash.setBlockHash(simpleBlockHashTicketDto.getBlockHash());
        blockHashRepository.save(newBlockHash);
        return "성공";
    }

    public SimpleBlockHashDto getBlockHash(Integer ticketId) {
        BlockHash foundBlockHash = blockHashRepository.findByTicketId(ticketId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));

        return new SimpleBlockHashDto(foundBlockHash.getBlockHash());
    }
}
