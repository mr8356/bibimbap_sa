package memo.service;

import lombok.RequiredArgsConstructor;
import memo.dto.request.MemoRequestDto;
import memo.dto.response.MemoListResponseDto;
import memo.dto.response.MemoResponseDto;
import memo.entity.Memo;
import memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
	private final MemoRepository memoRepository;



	public MemoListResponseDto getMemos(){
		List<Memo> memos = memoRepository.findAll();
		List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
		for (Memo memo : memos) {
			MemoResponseDto memoDto = MemoResponseDto.builder()
					.id(memo.getId())
					.title(memo.getTitle()).content(memo.getContent()).build();
			memoResponseDtos.add(memoDto);
		}
		return new MemoListResponseDto(memoResponseDtos);
	}

	public void addMemo(MemoRequestDto memoRequestDto) {
		Memo memoData = Memo.builder().title(memoRequestDto.getTitle()).content(memoRequestDto.getContent()).build();
		memoRepository.save(memoData);
	}
}
