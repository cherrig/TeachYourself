package project.teachyourself.model;

import java.util.List;

import okhttp3.RequestBody;
import project.teachyourself.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit user service to communicate with user api
 */
public interface UserApiService {

    /**
     * Retrieves user with email
     * @param email user's email
     * @return a response containing a user
     */
    @GET("/user")
    Call<User> getUserByEmail(@Query("email") String email);

    /**
     * Creates a new user
     * @param email new user's email
     * @param name new user's name
     * @param age new user's age
     * @param password new user's password
     * @return a response containing a potential error message
     */
    @Multipart
    @POST("/new")
    Call<String> insert(@Part("email") RequestBody email,
                        @Part("name") RequestBody name,
                        @Part("age") int age,
                        @Part("password") RequestBody password,
                        @Part("admin") boolean admin);

    /**
     * Tries to connect a user
     * @param email user's email
     * @param password user's password
     * @return a response containing a potential error message
     */
    @Multipart
    @POST("/connect")
    Call<String> connect(@Part("email") RequestBody email,
                         @Part("password") RequestBody password);

    /**
     * Retrieves statistics of a user or globally
     * @param email user's email or nothing for global statistics
     * @return a response containing a list a categories with statistics
     */
    @GET("/stat")
    Call<List<Category>> getStatistics(@Query("email") String email);


    /**
     * Saves user's statistics for a quiz
     * @param email user's email
     * @param category category of the quiz played
     * @param score number of good answer
     * @param questionNumber number of questions
     * @param avgResponseTime average response time
     * @return a response containing a potential error message
     */
    @Multipart
    @POST("/save_score")
    Call<String> save(@Part("email") RequestBody email,
                      @Part("category") RequestBody category,
                      @Part("score") int score,
                      @Part("questionNumber") int questionNumber,
                      @Part("avgResponseTime") float avgResponseTime);


    /**
     * Updates the user profile picture
     * @param email user's email
     * @param image user's profile picture
     * @return a response containing user's information
     */
    @Multipart
    @POST("/save_image")
    Call<User> saveImage(@Part("email") RequestBody email,
                         @Part("image") RequestBody image);

    /**
     * Retrieves all questions for a given category and level
     * @param category question's category
     * @param level question's level
     * @return list of all question
     */
    @GET("/questions/{category}/{level}")
    Call<List<Question>> getQuestions(@Path("category") String category,
                                      @Path("level") int level);

    /**
     * Create a new question
     * @param category question's category
     * @param level question's level
     * @param question question
     * @param rep1 answer 1
     * @param rep2 answer 2
     * @param rep3 answer 3
     * @param rep4 answer 4
     * @param correction correction
     * @return OK
     */
    @Multipart
    @POST("/questions/{category}/{level}")
    Call<String> addQuestion(@Path("category") String category,
                             @Path("level") int level,
                             @Part("question") RequestBody question,
                             @Part("rep1") RequestBody rep1,
                             @Part("rep2") RequestBody rep2,
                             @Part("rep3") RequestBody rep3,
                             @Part("rep4") RequestBody rep4,
                             @Part("correction") int correction);
}
