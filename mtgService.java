/*
<manifest ... >
  ...
  <application ... >
      <service android:name=".mtgServiceService" />
      ...
  </application>
</manifest>
*/

public class mtgService extends IntentService{

    public mtgService(){
        supper("mtgService");
    

    @override
    public void onStartCommand(){
    }
    
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        long endTime = System.currentTimeMillis() + 5*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                }
            }
        }
    }
