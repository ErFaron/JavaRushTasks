package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("temp", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            User user = new User();

            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setBirthDate(new Date());
            user.setCountry(User.Country.RUSSIA);
            user.setMale(true);

            javaRush.users.add(user);
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            if (loadedObject.equals(javaRush)) {
                javaRush = loadedObject;
            }
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (users.size() != 0) {
                for (User userOnly : users) {
                    printWriter.println(userOnly.getFirstName());
                    printWriter.println(userOnly.getLastName());
                    printWriter.println(userOnly.getCountry());
                    printWriter.println(userOnly.getBirthDate().getTime());
                    printWriter.println(userOnly.isMale());
                }
            }
            printWriter.flush();
            printWriter.close();
            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader printReader = new BufferedReader(new InputStreamReader(inputStream));
            while (printReader.ready()) {
                User user = new User();

                String fistName = printReader.readLine();
                String lastName = printReader.readLine();

                User.Country country = null;
                switch (printReader.readLine()) {
                    case "UKRAINE":
                        country = User.Country.UKRAINE;
                        break;
                    case "RUSSIA":
                        country = User.Country.RUSSIA;
                        break;
                    case "OTHER":
                        country = User.Country.OTHER;
                        break;
                }

                Date birthday = new Date(Long.parseLong(printReader.readLine()));

                boolean isMale = Boolean.parseBoolean(printReader.readLine());

                user.setLastName(lastName);
                user.setFirstName(fistName);
                user.setCountry(country);
                user.setMale(isMale);
                user.setBirthDate(birthday);

                users.add(user);
            }
            printReader.close();
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
