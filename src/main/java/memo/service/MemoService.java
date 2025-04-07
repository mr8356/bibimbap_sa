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

	public MemoResponseDto createMemo(MemoRequestDto request) {
		Memo memo = Memo.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.build();
		Memo saved = memoRepository.save(memo);
		return MemoResponseDto.builder()
				.id(saved.getId())
				.title(saved.getTitle())
				.content(saved.getContent())
				.build();
	}

	public MemoResponseDto getMemoById(Long id) {
		Memo memo = memoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 메모가 없습니다."));
		return MemoResponseDto.builder()
				.id(memo.getId())
				.title(memo.getTitle())
				.content(memo.getContent())
				.build();
	}

	public void deleteMemo(Long id) {
		memoRepository.deleteById(id);
	}


}
