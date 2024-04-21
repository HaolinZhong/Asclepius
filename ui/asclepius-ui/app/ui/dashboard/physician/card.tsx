import {inter, lusitana} from "@/app/lib/font";
import {UserCircleIcon} from "@heroicons/react/24/outline";

// export function Card({physician}) {
//     return (
//         <div className="rounded-xl bg-gray-50 p-2 shadow-sm">
//             <div className="flex p-2">
//                 <h3 className={`${lusitana.className} ml-2 text-2xl font-medium`}>{physician.name}</h3>
//             </div>
//             <p
//                 className="p-4 text-sm"
//             >
//                 {physician.description}
//             </p>
//         </div>
//     );
// }

export function Card({ physician }) {
    return (
        <div className="rounded-xl bg-gray-50 p-2 shadow-sm">
            <div className="flex justify-between items-center p-2">
                <div className="flex items-center space-x-2">
                    <UserCircleIcon className="w-10"/>
                    <h3 className={`${lusitana.className} ml-2 text-2xl font-medium`}>{physician.name}</h3>
                </div>
                <span className="text-xl text-gray-600 mr-2">{physician.specialty}</span>
            </div>
            <p className={`${inter.className} px-4 py-2 text-sm`}>
                {physician.description}
            </p>
        </div>
    );
}

