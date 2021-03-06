public class MovieRecommender {


    //This method checks if the password to the site has the require length,
    //at least 1 capital letter, 1 lower case letter, 1number,
    //and that all the characters are alphanumeric.

    public static boolean isSecurePassword(String password) {

        boolean length = false;
        boolean caps = false;
        boolean low_case = false;
        boolean number = false;
        boolean alphanumeric = true;
        boolean final_ans = false;


        // check length
        if (password.length() >= 10) {
            length = true;
        }

        //check capital letter
        for (int i = 0; i < password.length(); i++) {

            if ((password.codePointAt(i) > 64) && (password.codePointAt(i)) < 91) {
                caps = true;

            }
        }
        for (int i = 0; i < password.length(); i++) {
            if ((password.codePointAt(i) > 96) && (password.codePointAt(i)) < 123) {
                low_case = true;
            }
        }

        for (int i = 0; i < password.length(); i++) {

            if ((password.codePointAt(i) > 47) && (password.codePointAt(i)) < 58) {
                number = true;
            }
        }

        for (int i = 0; i < password.length(); i++) {
            if ((password.codePointAt(i) < 48) || (password.codePointAt(i)) > 57 && password.codePointAt(i) < 65 || (password.codePointAt(i)) > 90 && password.codePointAt(i) < 97 || (password.codePointAt(i)) > 122) {

                alphanumeric = false;
            }
        }

        if ((length) && (caps) && (low_case) && (number) && (alphanumeric)) {
            final_ans = true;

        }

        return final_ans;
    }


    //this method compares 2 users and returns a double indicating how close they are
    //A user is represented by an array containing that users ratings of several different movies
    // Based on the 2 users ratings of movies, we can determine how 'close' these two users are
    // with respect to their movie tastes.

    public static double compareUsers(int[] user1, int[] user2) {


        int[] normalizedUser1 = new int[user1.length];
        int[] normalizedUser2 = new int[user2.length];
        double magnitudeUser1 = 0.0;
        double magnitudeUser2 = 0.0;
        int dotp = 0;
        double theta;
        double temp;


        for (int i = 0; i < user1.length; i++) {
            normalizedUser1[i] = user1[i] - 5;
            normalizedUser2[i] = user2[i] - 5;
        }


        for (int i = 0; i < user1.length; i++) {
            magnitudeUser1 += Math.pow(normalizedUser1[i], 2);
            magnitudeUser2 += Math.pow(normalizedUser2[i], 2);


        }
        magnitudeUser1 = Math.sqrt(magnitudeUser1);
        magnitudeUser2 = Math.sqrt(magnitudeUser2);


        for (int i = 0; i < user1.length; i++) {

            dotp += normalizedUser1[i] * normalizedUser2[i];

        }
        temp = ((dotp) / (magnitudeUser1 * magnitudeUser2));

        if (temp > 1) {

            temp = 1;
        } else if (temp < -1) {

            temp = -1;
        }


        theta = Math.acos(temp);


        return theta;
    }

    //This method takes as input a user id and a 2d array which is an array
    //of users(Remember each uses is represented by an array of movies he/she has rated),
    // and a user id for which whe want to find the user nearest to.
    // This method calls the compareUsers method and returns the index
    //of the user nearest to the requested user.
    public static int findNearestUser(int[][] ratings, int userid) {
        int nearestIndex = -1;
        for (int i = 0; i < ratings.length; i++) {
            if (i != userid) {
                if (nearestIndex == -1 || compareUsers(ratings[userid], ratings[nearestIndex]) > compareUsers(ratings[userid], ratings[i])) {
                    nearestIndex = i;
                }
            }
        }
        System.out.println(nearestIndex);

        return nearestIndex;
    }


    //returns index of friendsratings array
    //containing highest rated movie by friend not rated by userratings (5)
    //find all users positions that 5 exists (movies not yet rated)
    // go to friends ratings and return the index of highest rated position for which user has a 5

    public static int chooseBestUnratedMovie(int[] userRatings, int[] friendRatings) {
        int temp[] = new int[userRatings.length];
        int index = 0;
        for (int i = 0; i < userRatings.length; i++) {
            if (userRatings[i] == 5) {
                temp[i] = i;
            }
        }

        for (int j = 0; j < userRatings.length; j++) {
            if (j == temp[j]) {
                if (friendRatings[j] > friendRatings[index]) {

                    index = j;
                }
            }

        }
        return index;
    }
}

