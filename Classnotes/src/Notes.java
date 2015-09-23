//		public void generateSentences(int numSentences, String startVar, String outputFile)
		//{
//		String[] sentences = new String[numSentences]; //array holding the sentences
//
//		Variable startKey = keyForVarName(startVar); //using helper method keyForVarName to retrieve key for start variable
//		
//		for(int i = 0; i < numSentences; i++){
//			String sentence = generateSentence(startKey, ""); //Recursion writing the sentences
//			sentences[i] = sentence; 
//		}
//		
//		try {
//			//printing the sentences
//			PrintWriter pw = new PrintWriter(new FileWriter(outputFile));
//			for(int i = 0; i < numSentences; i++){
//				pw.println(sentences[i]);
//			}
//			pw.close();
//		} catch (IOException e) {
//			System.out.println("Could not write file");
//		}	
//	}
//that uses
//this method
//public String generateSentence(Variable startVar, String curSentence){
//		
//		if(!rules.containsKey(startVar)) { //if does not contain mapping for start 
//
//			curSentence = curSentence + startVar.name; 
//			return curSentence;
//		} else{
//
//			ArrayList<RightSide> rightSides = rules.get(startVar);// array list of type RightSide that holds all right sides for the star variable
//
//			Random gen = new Random(); //random number generated
//			int random = gen.nextInt(rightSides.size());
//			RightSide rightSide = rightSides.get(random); //pick random right side
//			
//			int numVars = rightSide.rightSide.size();// number of variables for each variable
//			
//			for(int i = 0; i < numVars; i++){
//				
//				Variable curVar = keyForVarName(rightSide.get(i).name);
//				if(curVar.isTerminal){ //stopping condition is if it is a terminal
//					curSentence = curSentence + curVar.name; //if left with variable, stop
//				}
//				else {
//					curSentence = generateSentence(curVar, curSentence); //recurse until you hit a terminal
//				}
//				
//			}
//			
//			return curSentence;
//			
//		}
