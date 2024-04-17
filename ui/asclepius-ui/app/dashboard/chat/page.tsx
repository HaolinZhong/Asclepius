import MessageList from "@/app/ui/dashboard/chat/message-list";
import MessageForm from "@/app/ui/dashboard/chat/message-form";
import {ChatContextProvider} from "@/app/ui/dashboard/chat/chat-context";

export default function chatPage() {
    return (
        <div>
            <ChatContextProvider>
                <MessageList/>
                <div className="fixed bottom-0 right-0 left-0">
                    <MessageForm />
                </div>
            </ChatContextProvider>
        </div>
    )
}