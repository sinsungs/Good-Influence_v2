// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';


function MyPage() {

    // Recoil을 사용하여 JWT 토큰을 가져옵니다
    const jwtToken = useRecoilValue(tokenState); 
    
    const [meets, setMeets] = useState([]);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/api/meeting/list', {
          headers: {
            Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
          },
        })
        .then(response => {
            setMeets(response.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });

          // 주문내역 불러오기
          ordersLoading();
    }, []);

    const ordersLoading = () => {
      axios.get(`/api/order/list`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })
      .then(response => {
        setOrders(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }


    const deleteMeeting = (meetid) => {

      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }

      
      alert('소셜 모임을 취소 하시겠습니까 ?')
    
      axios.delete(`/api/meeting/delete/${meetid}`,
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })

        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log(response.data);
          alert(response.data);
          window.location.reload();
          // handleModalClose();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });

    }

    function formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Month is zero-based
      const day = date.getDate().toString().padStart(2, '0');
      const hours = date.getHours().toString().padStart(2, '0');
      const minutes = date.getMinutes().toString().padStart(2, '0');
      const seconds = date.getSeconds().toString().padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
    
    
    

return (
    <div className="App">
        <div className='back'>
            <div className="container">
            <div className="search">
            {/* <button className="login-button" onClick={handleRectangleClick}>
                소셜 모임 만들기
            </button> */}
            <h1>소셜 모임 신청 내역</h1>
            </div>
                <ul>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>모임 시간</p>
                            </div>
                            <div class="flex-item-center-one">
                                <p>모임 지역</p>
                            </div>
                            <div class="flex-item-center-two">
                                <p>모임장</p>
                            </div>
                            <div class="flex-item-center">
                                <p>모임 내용</p> 
                            </div> 
                            <div class="flex-item-right">
                                {/* <div class="full"> */}
                                    <p>신청 여부</p>
                                {/* </div> */}
                            </div>
                    </li>
                    <hr/>

                    {meets.map(meets => (
                    <div>
                    <li class="flex-container" key={meets.meetid}>
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{meets.meettime.replace('T', '  ')}</p>
                            </div>
                            <div class="flex-item-center-one">
                                <h3>{meets.region}</h3>
                            </div> 
                            <div class="flex-item-center-two">
                            <h3 style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
                              {meets.writer}
                            </h3>
                            </div> 
                            <div class="flex-item-center">
                                <h3>{meets.title}</h3>
                                <span>{meets.content}</span> 
                            </div> 

                            <div class="flex-item-right">
                                <div class="full">
                                    현재 : {meets.currentPlayers}명 / {meets.maxplayers}명
                                    <p>{meets.result}</p>
                                    <button onClick={() => deleteMeeting(meets.meetid)} style={{backgroundColor:"red"}}>취소하기</button>

                                </div>
                            </div>

                              

                    </li>

                    <hr/>


                    </div>

))}
                <div>
                  <h1>결제 내역 </h1>
                </div>
                <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>결제 시간</p>
                            </div>
                            <div class="flex-item-center-one">
                                <p>모임 지역</p>
                            </div>
                            <div class="flex-item-center-two">
                                <p>모임장</p>
                            </div>
                            <div class="flex-item-center">
                                <p>모임 내용</p> 
                            </div> 
                            <div class="flex-item-right">
                                {/* <div class="full"> */}
                                    <p>결제 금액</p>
                                {/* </div> */}
                            </div>
                    </li>
                    <hr/>
{orders.map(order => (
                    <div>
                    <li class="flex-container" key={order.orderid}>
  
                            <div class="flex-item-left">
                                <p>{formatDate(order.orderDate)}</p>
                            </div>
                            <div class="flex-item-center-one">
                                <h3>{order.region}</h3>
                            </div> 
                            <div class="flex-item-center-two">
                            <h3 style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
                              {order.writer}
                            </h3>
                            </div> 
                            <div class="flex-item-center">
                                <h3>{order.title}</h3>
                                <span>{order.content}</span> 
                            </div> 

                            <div class="flex-item-right">
                                <div class="full">
                                    <p>{order.price}</p>
                                </div>
                            </div>

                              

                    </li>

                    <hr/>


                    </div>

))}
                </ul>



              

            </div>
        </div>
    </div>
);
}

export default MyPage;
