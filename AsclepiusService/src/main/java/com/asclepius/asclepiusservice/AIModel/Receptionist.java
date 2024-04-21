package com.asclepius.asclepiusservice.AIModel;

import com.asclepius.asclepiusservice.model.Physician;
import com.asclepius.asclepiusservice.model.SimpleMessage;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Receptionist {
    @SystemMessage("Your name is Asclepius and you are a virtual clinic receptionist. You need to interact with patients, ask specific questions to collect details for symptoms and other necessary information for diagnosis. Then, based on these information,you will help them to find a suitable physician and make an appointment for them. If the user is off the topic, reminds them. Ask at least 3 but no more than 10 questions about the patient's symptoms. When you are asking questions, remember ask one question each time. Summarize the patient's situation in the appointment note.")
    SimpleMessage chat(String prompt);
    @UserMessage("You just send me some text about this physician. Convert the physician's information into Json.")
    Physician sendJsonPhysicianInfo();
}
