import MessageList from "@/app/ui/dashboard/chat/message-list";
import MessageForm from "@/app/ui/dashboard/chat/message-form";
import {ChatContextProvider} from "@/app/ui/dashboard/chat/chat-context";

export default function ChatPage() {
    return (
        <div className="flex flex-col flex-grow justify-between items-center"> {/* 适应SideNav的响应式左边距 */}
            <ChatContextProvider>
                <div className="flex flex-col flex-grow">
                    <div className="px-4 py-50  overflow-y-auto">
                        <MessageList className="flex-grow" />
                    </div>
                    <div className="inset-x-0 bottom-0 px-4 py-0">
                        <MessageForm className="max-w-xl w-full mx-auto" />
                    </div>
                </div>
            </ChatContextProvider>
        </div>
    );
}