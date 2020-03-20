package com.filmland.streaming.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmland.streaming.exception.DataNotFoundException;
import com.filmland.streaming.model.FilmCategoryDto;
import com.filmland.streaming.model.ShareSubscription;
import com.filmland.streaming.model.UserDto;
import com.filmland.streaming.repository.FilmCategoryRepository;
import com.filmland.streaming.repository.ShareFilmCategoryRepository;
import com.filmland.streaming.repository.UserFilmCategoryRepository;
import com.filmland.streaming.repository.UserRepository;
import com.filmland.streaming.repository.entities.FilmCategory;
import com.filmland.streaming.repository.entities.ShareFilmCategory;
import com.filmland.streaming.repository.entities.ShareFilmCategoryId;
import com.filmland.streaming.repository.entities.UserFilmCategory;
import com.filmland.streaming.repository.entities.UserFilmCategoryId;
import com.filmland.streaming.repository.entities.UserInfo;
import com.filmland.streaming.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FilmCategoryRepository filmCategoryRepository;

	@Autowired
	UserFilmCategoryRepository userFilmCategoryRepository;

	@Autowired
	ShareFilmCategoryRepository shareFilmCategoryRepository;

	@Autowired
	private Mapper dozerBeanMapper;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/*
	 * @Autowired private UserFilmCategoryRepository userFilmCategoryRepository;
	 */

	@Override
	public UserDto findUserByMail(String email) throws DataNotFoundException {
		UserInfo userInfo = userRepository.findUserByEmail(email);
		if (null == userInfo) {
			throw new DataNotFoundException("User Does not exists");
		} else {
			return getUserSubscriptions(userInfo);
		}

	}

	@Override
	public UserDto findUserByUsername(String username) throws DataNotFoundException {
		UserInfo userInfo = userRepository.findUserByUsername(username);
		System.out.println("User Info :" + userInfo);
		if (null == userInfo) {
			throw new DataNotFoundException("User Does not exists");
		} else {
			return getUserSubscriptions(userInfo);
		}
	}

	private UserDto getUserSubscriptions(UserInfo userInfo) {
		List<UserFilmCategory> userFilmCategories = userInfo.getUserFilmCategories();
		// System.out.println(c);
		UserDto userDto = dozerBeanMapper.map(userInfo, UserDto.class);
		List<FilmCategoryDto> userFilmCategoriesDto = new ArrayList<>();
		if (null != userFilmCategories) {

			userFilmCategories.forEach(userFilmCategory -> {
				FilmCategory filmCategory = userFilmCategory.getFilmCategory();
				FilmCategoryDto filmCategoryDto = dozerBeanMapper.map(filmCategory, FilmCategoryDto.class);
				filmCategoryDto.setRemainingContent(filmCategoryDto.getAvailableContent());
				filmCategoryDto.setAvailableContent(null);
				LocalDate localDate = userFilmCategory.getStartDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				filmCategoryDto.setStartDate(formatter.format(localDate));
				userFilmCategoriesDto.add(filmCategoryDto);
			});
			userDto.setUserFilmCategories(userFilmCategoriesDto);
		} else {
			userDto.setUserFilmCategories(null);
		}
		return userDto;
	}

	@Override
	public boolean saveSubscription(String email, String availableCategory) throws Exception {

		try {
			UserInfo userInfo = userRepository.findUserByEmail(email);
			FilmCategory filmCategory = filmCategoryRepository.findByName(availableCategory);
			UserFilmCategory userFilmCategory = new UserFilmCategory();
			//userFilmCategory.setUserInfo(userInfo);
			//userFilmCategory.setFilmCategory(filmCategory);
			UserFilmCategoryId userFilmCategoryId = new UserFilmCategoryId();
			userFilmCategoryId.setUserId(userInfo.getUserId());
			userFilmCategoryId.setFilmCategoryId(filmCategory.getFilmCategoryId());
			userFilmCategory.setId(userFilmCategoryId);
			userFilmCategory.setStartDate(new Date());
			userFilmCategoryRepository.save(userFilmCategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean shareSubscription(ShareSubscription shareSubscription) throws Exception {
		try {
			UserInfo userInfo = userRepository.findUserByEmail(shareSubscription.getEmail());
			UserInfo custInfo = userRepository.findUserByEmail(shareSubscription.getCustomer());
			FilmCategory filmCategory = filmCategoryRepository.findByName(shareSubscription.getSubscribedCategory());
			ShareFilmCategory shareFilmCategory = new ShareFilmCategory();
			/*
			 * shareFilmCategory.setUserInfo(userInfo);
			 * shareFilmCategory.setCustInfo(custInfo);
			 * shareFilmCategory.setFilmCategory(filmCategory);
			 */
			ShareFilmCategoryId shareFilmCategoryId = new ShareFilmCategoryId();
			shareFilmCategoryId.setUserId(userInfo.getUserId());
			shareFilmCategoryId.setCustId(custInfo.getUserId());
			shareFilmCategoryId.setFilmCategoryId(filmCategory.getFilmCategoryId());
			shareFilmCategory.setId(shareFilmCategoryId);
			shareFilmCategoryRepository.save(shareFilmCategory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
