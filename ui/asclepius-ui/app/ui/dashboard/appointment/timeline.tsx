'use client'

const getAppointments = async () => {
    const response = await fetch('http://localhost:3000/api/appointment');
    const appointments = await response.json();
    return appointments;
}

export default async function Timeline() {

    const appointments = await getAppointments();

    return (
        <div className="container mx-auto px-4">
            <h1 className="text-center font-bold text-xl my-4">My Appointment</h1>
            <div className="relative border-l-4 border-blue-500">
                {appointments.map((appointment, index) => (
                    <div key={index} className="mb-8 ml-4">
                        <div
                            className="absolute w-3 h-3 bg-blue-500 rounded-full mt-1.5 -left-1.5 border border-white"></div>
                        <div className="flex items-center text-sm font-medium text-blue-500 mb-1">
                            {appointment.date}
                        </div>
                        <div className="bg-white p-4 rounded shadow">
                            <h3 className="text-lg font-bold">{appointment.physician}</h3>
                            <h4 className="text-sm text-gray-600">{appointment.specialty}</h4>
                            <p className="text-gray-800">{appointment.note}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )

}
