import React, {useState} from "react";

function loginPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function handleLogin(e) {
        e.preventDefault();
        console.log("logging in with:", {username, password});
    }



    return(
        <>
            <div className="min-h-screen flex items-center justify-center bg-gray-100">
                <h2 className="text-2xl font-bold mb-6 text-center text-gray-800">
                    login
                </h2>

                <form onSubmit={handleLogin} className="space-y-4">
                    {/* fields for username */}
                    <div className="block text-sm font-medium text-gray-700">
                        username
                    </div>
                    <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-50"
                    placeholder="enter username"
                    required
                    >
                    </input>

                    {/* fields for password */}
                    <div className="block text-sm font-medium text-gray-700">
                        password
                    </div>

                    <input
                    type="text"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-50"
                    placeholder="enter password"
                    required
                    >
                    </input>

                    {/* submit button */}
                    <button
                    type="submit"
                    className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition duration-200 font-semibold"
                    >
                    Log In
                    </button>

                </form>
            </div>
        </>
    )
}

export default loginPage;