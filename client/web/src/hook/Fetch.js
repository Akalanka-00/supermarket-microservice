import { useEffect, useState } from "react";
import baseUrl from "../Apis/baseUrl";

export default function useFetch(req) {
  const { query, reqData, method } = req;

  const [getData, setData] = useState({
    isLoading: false,
    apiData: [],
    status: null,
    serverError: null,
  });

  useEffect(() => {
   // console.log(getData.req);
    const fetchData = async () => {
      try {
        setData((prev) => ({ ...prev, isLoading: true }));

        if (method === "get") {
          const { data, status } = await baseUrl.get(`/${query}`);
        //  console.log(data, status);

          if (status === 200) {
            setData((prev) => ({ ...prev, isLoading: false }));
            setData((prev) => ({ ...prev, apiData: data, status: status }));
          }

          setData((prev) => ({ ...prev, isLoading: false }));
        }

        if (method === "post") {
          const { data, status } = await baseUrl.post(`/${query}`, reqData);
       //   console.log(data, status);

          if (status === 200) {
            setData((prev) => ({ ...prev, isLoading: false }));
            setData((prev) => ({ ...prev, apiData: data, status: status }));
          }

          setData((prev) => ({ ...prev, isLoading: false }));
        }
      } catch (error) {
        console.log(error.message + " in " + query);
        setData((prev) => ({ ...prev, isLoading: false, serverError: error }));
      }
    };
    fetchData();
  }, [query]);

  return [getData, setData];
}
