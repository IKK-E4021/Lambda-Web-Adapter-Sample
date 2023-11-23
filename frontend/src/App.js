import { useEffect, useState } from "react";
import axios from "axios";

function App() {

  const instance = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      'Content-Type': 'application/json'
    },
  });

  const [data, setData] = useState([]);

  useEffect(() => {
    const getData = async () => {
      const res = await instance.get("/api/users/1")
      setData(res.data);
    };
    getData();
  }, []);

  return (
    <>
      <h1>Hello World! {data.name}</h1>
    </>
  );
}

export default App;
