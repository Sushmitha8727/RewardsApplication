import React, { Component } from 'react';

class App extends Component {
  totalPoints ;

  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = {
    rewards: [],
    input:''    
    }
  }
   
  handleChange(e) {
    this.setState({ input: e.target.value });
  }

  handleClick(){
    fetch('http://localhost:8080/getRewards?name='+this.state.input)
    .then(res => res.json())
    .then((data) => {
        this.totalPoints = data.totalPoints;
        this.setState(
          {rewards:data.rewards}
        )     
    })
    .catch(console.log)
  }

render() {
  
    return (
       <div className="container">
        <div className="col-xs-12">
        <h1>My Rewards</h1>

        <div>
        <input type="text" onChange={ this.handleChange } />
        <input
          type="button"
          value="Get Rewards"
          onClick={this.handleClick}
        />
      </div>

          <h4>Totatl Points : {this.totalPoints} </h4>
          <h5> Rewards</h5>
         
            {this.state.rewards.map((r) => (
              <div> Month :  {r.month}  <br/>
                          Month Points :  {r.monthPoints}<br/><br/></div> 
            ))}          
       
        </div>
       </div>
    );
  }

}
export default App;