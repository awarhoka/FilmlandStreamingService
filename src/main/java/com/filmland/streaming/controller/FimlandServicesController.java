package com.filmland.streaming.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmland.streaming.exception.DataNotFoundException;
import com.filmland.streaming.model.FilmCategoryDto;
import com.filmland.streaming.model.MessageResponse;
import com.filmland.streaming.model.ShareSubscription;
import com.filmland.streaming.model.SubscribeFilmCategory;
import com.filmland.streaming.model.UserDto;
import com.filmland.streaming.service.FilmCategoryService;
import com.filmland.streaming.service.UserService;

@RestController
//@CrossOrigin()
public class FimlandServicesController {

	@Autowired
	FilmCategoryService filmCategoryService;

	@Autowired
	UserService userService;

	@GetMapping(path = "/subinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getAvailableAndSubscribedFilmCategories(
			@RequestParam(required = true) String username) {
		Map<String, List<FilmCategoryDto>> map = new HashMap<>();

		if (StringUtils.isBlank(username)) {
			return new ResponseEntity<String>("Invalid Request", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<FilmCategoryDto> availableCategories = filmCategoryService.retriveAll();
			UserDto user = userService.findUserByUsername(username);
			if (null != user) {
				List<FilmCategoryDto> subscribedCategories = user.getUserFilmCategories();

				/*
				 * List<FilmCategoryDto> remainAvailCats = availableCategories.stream().filter(
				 * aCat -> (
				 * subscribedCategories.stream().map(FilmCategoryDto::getName).collect(
				 * Collectors.toList())) .contains(aCat.getName()))
				 * .collect(Collectors.toList());
				 */

				List<FilmCategoryDto> remainAvailCats = availableCategories.stream()
						.filter(aCat -> subscribedCategories.stream()
								.allMatch(sCat -> !sCat.getName().equalsIgnoreCase(aCat.getName())))
						.collect(Collectors.toList());
				map.put("availableCategories", remainAvailCats);
				map.put("subscribedCategories", subscribedCategories);
			}

		} catch (DataNotFoundException e) {
			System.out.println("Exception Here" + e);
		}

		return new ResponseEntity<Map<String, List<FilmCategoryDto>>>(map, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> subscribeFilmCategory(@RequestBody SubscribeFilmCategory userFilmCategory) {
		MessageResponse message = new MessageResponse();
		boolean isSuccess = false;

		try {
			UserDto user = userService.findUserByMail(userFilmCategory.getEmail());
			if (null != user) {
				List<FilmCategoryDto> subCats = user.getUserFilmCategories();
				if (null != subCats && subCats.size() > 0) {

					Boolean isExists = subCats.stream().map(FilmCategoryDto::getName).collect(Collectors.toList())
							.contains(userFilmCategory.getAvailableCategory());

					if (isExists) {
						message.setStatus("Subscription Exists");
						message.setMessage(
								"Subscription Already Exists for " + userFilmCategory.getAvailableCategory());
						return new ResponseEntity<MessageResponse>(message, new HttpHeaders(),
								HttpStatus.ALREADY_REPORTED);
					}
					FilmCategoryDto filmCategoryDto = filmCategoryService
							.findByName(userFilmCategory.getAvailableCategory());
					if (null == filmCategoryDto) {
						message.setStatus("Category Not Available");
						message.setMessage("Category " + userFilmCategory.getAvailableCategory()
								+ " not available for Subscription");
						return new ResponseEntity<MessageResponse>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
					}
				}
				try {
					isSuccess = userService.saveSubscription(userFilmCategory.getEmail(),
							userFilmCategory.getAvailableCategory());
				} catch (Exception e) {
					isSuccess = false;
				}
			}

		} catch (Exception e) {
			System.out.println("Ashish Exception Here");
		}

		if (isSuccess) {
			message.setStatus("Subscription successfull");
			message.setMessage("You have SuccessFully Subscribed to " + userFilmCategory.getAvailableCategory());
			return new ResponseEntity<MessageResponse>(message, new HttpHeaders(), HttpStatus.OK);
		} else {
			message.setStatus("Subscription Failed");
			message.setMessage("Your Subscription Failed for " + userFilmCategory.getAvailableCategory());
			return new ResponseEntity<MessageResponse>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "/share", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> shareSubscription(@RequestBody ShareSubscription shareSubscription) {
		MessageResponse message = new MessageResponse();
		boolean isSuccess= false;
		try {
		   isSuccess = userService.shareSubscription(shareSubscription);
		} catch (Exception e) {
			isSuccess =false;
		}
		if (isSuccess) {
			message.setStatus("Share successfull");
			message.setMessage("You successFully shared " + shareSubscription.getSubscribedCategory()
					+ " Film Category with " + shareSubscription.getCustomer());
			return new ResponseEntity<MessageResponse>(message, new HttpHeaders(), HttpStatus.OK);
		} else {
			message.setStatus("Share Failed");
			message.setMessage("Sharing Failed " + shareSubscription.getSubscribedCategory() + " Film Category with "
					+ shareSubscription.getCustomer());
			return new ResponseEntity<MessageResponse>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}