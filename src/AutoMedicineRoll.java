public class AutoMedicineRoll{
    final private static int DICE_PER_CRIT_SUCCESS = 4;
    final private static int DICE_PER_SUCCESS = 2;
    final private static int DICE_PER_FAIL = 0;
    final private static int DICE_PER_CRIT_FAIL = -1;
    final private static int DICE_SIDES = 8;
    final private static int DC = 15;

    private int modifier;
    private boolean wardMedic;
    private boolean continualRecovery;

    public AutoMedicineRoll(){
        wardMedic = false;
        continualRecovery = false;
        modifier = 0;
    }

    /**
     * Input your character's medicine modifier
     * @param modifier
     */
    public AutoMedicineRoll(int modifier){
        wardMedic = false;
        continualRecovery = false;
        this.modifier = modifier;
    }

    /**
     * Input your character's medicine modifier and if they have the Ward Medic and/or the Continual Recovery feats
     * @param modifier
     * @param wardMedic
     * @param continualRecovery
     */
    public AutoMedicineRoll(int modifier, boolean wardMedic, boolean continualRecovery){
        this.wardMedic = wardMedic;
        this.continualRecovery = continualRecovery;
        this.modifier = modifier;
    }

    /**
     * Roll medicine checks
     * Prints out the healing
     * @param rollsPerPlayer
     * @param players
     * @throws IllegalArgumentException
     */
    public void printRoll(int rollsPerPlayer, int players) throws IllegalArgumentException{
        if(rollsPerPlayer<0) throw new IllegalArgumentException("Cannot have a negative number of rolls");
        if(players<=0) throw new IllegalArgumentException("Must have at least 1 player");
        for(int i = 1; i <= players; i++){
            int totalHealth = 0;
            for(int j = 0; j < rollsPerPlayer; j++){
                int rolled = ((int)(Math.random()*20))+1+modifier;
                if(rolled>=DC+10){
                    for(int k = 0; k < Math.abs(DICE_PER_CRIT_SUCCESS); k++){
                        totalHealth+=((int)(((Math.random()*DICE_SIDES)+1)*Math.signum(DICE_PER_CRIT_SUCCESS)));
                    }
                }
                else if(rolled>=DC){
                    for(int k = 0; k < Math.abs(DICE_PER_SUCCESS); k++){
                        totalHealth+=((int)(((Math.random()*DICE_SIDES)+1)*Math.signum(DICE_PER_SUCCESS)));
                    }
                }
                else if(rolled>=DC-10){
                    for(int k = 0; k < Math.abs(DICE_PER_FAIL); k++){
                        totalHealth+=((int)(((Math.random()*DICE_SIDES)+1)*Math.signum(DICE_PER_FAIL)));
                    }
                }
                else{
                    for(int k = 0; k < Math.abs(DICE_PER_CRIT_FAIL); k++){
                        totalHealth+=((int)(((Math.random()*DICE_SIDES)+1)*Math.signum(DICE_PER_CRIT_FAIL)));
                    }
                }
            }
            System.out.println("Player "+i+": "+totalHealth);
        }
    }

    public static void main(String[] args){
        AutoMedicineRoll Celne = new AutoMedicineRoll(7);
        Celne.printRoll(4, 1);
    }
}
