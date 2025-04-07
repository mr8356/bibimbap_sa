package memo.service;

import lombok.RequiredArgsConstructor;
import memo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;


}
