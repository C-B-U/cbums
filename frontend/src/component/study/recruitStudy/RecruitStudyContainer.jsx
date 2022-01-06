import React, { PureComponent } from "react";
import RecruitStudyPost from "./RecruitStudyPost";
import axios from "axios";
class RecruitStudyContainer extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      studyName: "",
      userName: "",
      recruit: true,
      maxMember: "",
      postDate: "",
    };
    this.getStudy = this.getStudy.bind(this);
  }
  getStudy = () => {
    axios.get("localhost:8000/api/project")
      .then(res => {
        // this.setState({
        //   studyName: res.name,
        //   userName: res.producer,
        //   recruit: res.producerHidden,
        //   maxMember: res.maximumMember,
        //   postDate: res.registerDatetime,
        // });
        console.log(res.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }
  componentDidMount() {
    this.getStudy();
  }
    render() {
        return (
          <React.Fragment>
           <RecruitStudyPost
             studyName="프로젝트 하실분 구해요"
              userName="이부엉"
              postDate="2021.11.16" />
            <RecruitStudyPost
              studyName="자바 스터디 모집합니다"
              userName="김부엉"
              postDate="2021.11.15"
            />
          </React.Fragment>
        );
    }
}

export default RecruitStudyContainer;
