import React, { useState, useEffect } from 'react';

import axios from 'axios';
import { useRecoilSnapshot, useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';

function PostReviewRead() {

    const { prno } = useParams();

  const navigate = useNavigate();
  const jwtToken = useRecoilValue(tokenState);

  const [comments, setComments] = useState([]);
  const [Posts, setPosts] = useState('');
  const [content, setContent] = useState(''); // 댓글 작성 상태 추가

  useEffect(() => {
    axios.get(`/api/postreview/read/${prno}`,)
      .then(response => {
        setPosts(response.data);
        console.log(Posts);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });

        // 댓글 불러오기
        commentLoading();
  }, []);

  const commentLoading = () => {
    axios.get(`/api/comment/list/${prno}`)
    .then(response => {
        setComments(response.data);
    })
    .catch(error => {
      console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
    });
}


  const deleteComment = (prno) => {
    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }
    alert('추천게시글 삭제 하시겠습니까 ?');
    axios.delete(`/api/comment/delete/${prno}`)
      .then(response => {
        console.log(response.data);
        alert(response.data);
        window.location.reload();
      })
      .catch(error => {
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

//   const handleRectangleClick = () => {
//     if (!jwtToken) {
//       alert('로그인이 필요합니다.');
//       return;
//     }
//     navigate('/reviewpost');
//   };

const handleCommentSubmit = () => {
    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }

    const contentData = {

        content,

      };
    // 서버로 새 댓글 전송
    axios.post(`/api/comment/register/${prno}`, contentData, {
    headers: {
        Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
    })
      .then(response => {
        console.log(response.data);
        alert(response.data);
        setContent(''); // 댓글 작성 후 입력란 비우기
        commentLoading(); // 댓글 작성 후 목록 다시 불러오기
      })
      .catch(error => {
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

  return (
    <div className="App">
      <div className="back">
        <div className="container">
          <div className="search">
            {/* <button className="login-button" onClick={handleRectangleClick}>
              후기 게시글 작성
            </button> */}
          </div>
          <div className="board" style={{ width: '100%', height: '700px', border: '3px solid #ccc' }}>
            <ul>
                <div className="post" style={{ height: '200px', margin: '20px' ,border: '1px solid #ccc'}}>
                    <h1>제목 : {Posts.title}</h1>
                    <h2>내용 : {Posts.content}</h2>
                    <img src={Posts.imageurl} alt='image' style={{width:'100px', height:'100px'}} />
                    <div>작성자 : {Posts.writer}</div>
                </div>
                <div>
          {/* <input
            type="text"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="댓글 작성"
          /> */}
           <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="댓글 작성"
        rows={4} // 여러 줄 표시를 위해 지정 (원하는 높이에 따라 조절)
        cols={100} // 가로 길이 지정 (원하는 길이에 따라 조절)
      />
          <button onClick={handleCommentSubmit}>작성</button>
        </div>
                <div>
                    <h1>댓글 목록</h1>
                </div>

              {comments.map((comment) => (
                <li key={comment.cno} style={{ width: '100%', height: '70px', border: '1px solid #ccc', display: 'flex', alignItems: 'center' }}>
                  <div className="board-list" style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
                    <div className="board-content">
                      <p>
                        <strong style={{ fontSize: '20px' }}>{comment.content}</strong>
                      </p>
                      <p>
                        <strong>작성자 :</strong> {comment.writer}
                      </p>
                    </div>
                    <div className="board-actions">
                      <button
                        onClick={() => deleteComment(comment.cno)}
                        style={{ backgroundColor: 'red' }}
                      >
                        댓글 삭제
                      </button>
                    </div>
                  </div>
                </li>
              ))}



            </ul>
        
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostReviewRead;
