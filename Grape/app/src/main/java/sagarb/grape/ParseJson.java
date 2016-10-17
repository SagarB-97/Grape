package sagarb.grape;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 9/22/2015.
 */
public class ParseJson {
    public static String[] names;
    public static String[] emails;
    public static String[] colleges;
    public static String[] profs;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ARRAY = "results";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_COLLEGE = "college";
    public static final String KEY_PROF = "profession";

    private JSONArray users = null;

    private String json;

    public ParseJson(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            String array = jsonObject.getString(KEY_ARRAY);
            array = "{\"result\""+":"+array+"}";
            jsonObject = new JSONObject(array);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            names = new String[users.length()];
            emails = new String[users.length()];
            colleges = new String[users.length()];
            profs = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                JSONObject fields = jo.getJSONObject("fields");
                names[i] = fields.getString(KEY_NAME);
                emails[i] = fields.getString(KEY_EMAIL);
                colleges[i]=fields.getString(KEY_COLLEGE);
                profs[i] = fields.getString(KEY_PROF);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
