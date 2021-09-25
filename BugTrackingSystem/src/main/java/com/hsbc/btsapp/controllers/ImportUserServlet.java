package com.hsbc.btsapp.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.hsbc.btsapp.beans.User;
import com.hsbc.btsapp.beans.enums.UserTypes;
import com.hsbc.btsapp.exceptions.EmailFormatValidationFalied;
import com.hsbc.btsapp.exceptions.UserAlreadyExistsException;
import com.hsbc.btsapp.factory.DAOFactory;
import com.hsbc.btsapp.services.ValidationServices;

@MultipartConfig

@WebServlet("/importUsers")
public class ImportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errMessage = "Something went wrong";

	public ImportUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean status = false;
		List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()))
				.collect(Collectors.toList());
		System.out.println(fileParts.size());
		for (Part part : fileParts) {
			String fileName = part.getSubmittedFileName();
			System.out.println(fileName);
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println(ext);
			String tmpFileName = null;
			File uploads = new File("C:\\Users\\mkaka\\Desktop\\demo");
			System.out.println(fileName);
			switch (ext) {
			case "json":
				tmpFileName = "tmp.json";
				break;
			case "xml":
				tmpFileName = "tmp.xml";
				break;
			default:
				throw new IllegalArgumentException("Invalid file format");

			}
			File file = new File(uploads, tmpFileName);

			try {
				InputStream input = part.getInputStream();
				Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				if (ext.equalsIgnoreCase("json"))
					status = processJsonFile(file);
				else
					status = processXMLFile(file);
				input.close();
			} catch (EmailFormatValidationFalied e) {
				errMessage = e.getMessage();
				status = false;
			} finally {
				file.delete();
			}

		}

		if (status == true) {
			System.out.println("User added successfully");
			request.setAttribute("successMessage", "Users added to database successfully");
		} else {
			request.setAttribute("errMessage", errMessage);
		}
		request.getRequestDispatcher("Homepage.html").forward(request, response);

	}

	// parse XML file to database
	private boolean processXMLFile(File file) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		boolean status = false;
		try {
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();

			NodeList list = document.getElementsByTagName("users");

			for (int index = 0; index < list.getLength(); index++) {

				Node node = list.item(index);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					String id = element.getAttribute("id");

					// get text
					String username = element.getElementsByTagName("username").item(0).getTextContent();
					String usertype = element.getElementsByTagName("usertype").item(0).getTextContent();
					String useremail = element.getElementsByTagName("useremail").item(0).getTextContent();

					System.out.println("Current Element :" + node.getNodeName());
					System.out.println("Staff Id : " + id);
					System.out.println("First Name : " + username);
					System.out.println("Last Name : " + usertype);
					System.out.println("Nick Name : " + useremail);
					if (!ValidationServices.validateEmail(useremail))
						return status;
					DAOFactory.getUserDAOImpl().addUser(new User(username, useremail, UserTypes.valueOf(useremail)));

				}
			}
			status = true;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserAlreadyExistsException ue) {
			ue.printStackTrace();
		}
		return status;
	}

	// parse Json file to database
	private boolean processJsonFile(File file) throws EmailFormatValidationFalied {

		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(file));
			JSONArray list = (JSONArray) obj;
			System.out.println(list);

			List<User> userList = new ArrayList<>();
			for (Object user : list) {
				JSONObject jsonUser = (JSONObject) user;
				String userName = (String) jsonUser.get("userName");
				String emailId = (String) jsonUser.get("emailId");
				UserTypes userType = UserTypes.getUserType((String) jsonUser.get("userType"));
				if (!ValidationServices.validateEmail(emailId)) {
					throw new EmailFormatValidationFalied("Some email format is invalid");
				}
				userList.add(new User(userName, emailId, userType));
			}
			try {
				DAOFactory.getUserDAOImpl().importUser(userList);
			} catch (UserAlreadyExistsException e) {
				System.out.println(e.getMessage());
				return false;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
}
