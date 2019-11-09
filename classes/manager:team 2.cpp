#include <iostream>

using namespace std;

struct manager{
	bool approve;
	bool deny;
	bool send;
	bool update_items;
	int priority_level;

	manager(bool approve, bool deny, bool send; bool update_items, int priority_level){
		this->approve = approve;
		this-> deny = deny;
		this-> send = send;
		this-> send = send;
		this-> priority_level = priority_level;
	}

	manager(){
		approve = false;
		deny = false;
		send = false;
		update_items = false;
		priority_level = 0;
	}


	bool getApprove(){
		return approve;
	}
	bool getDeny(){
		return deny;
	}
	bool getSend(){
		return send;
	}
	bool get_update_items(){
		return update_items;
	}
	bool get_priority_level(){
		return priority_level;
	}

	void setApprove(bool approve){
		this->approve = approve;
	}
	void setDeny(bool deny){
		this-> deny = deny;
	}
	void set_send(bool send){
		this-> send = send;
	}
	void setUpdate_items(bool update_items){
		this-> send = send;
	}
	void set_priority_level(int priority_level){
		this-> priority_level = priority_level;
	}

	
};

struct team{
	bool read;
	bool priority_level
	bool fix;

	team(bool read, priority_level, fix){
		this-> read = read;
		this-> priority_level = priority_level;
		this -> fix = fix;
	}

	team(){
		read = false;
		priority_level = 0;
		fix = false;
	}

	bool getRead(){
		return read;
	}
	int get_priority_level(){
		return priority_level;
	}
	bool getFix(){
		return fix;
	}

	void setRead(bool read){
		this -> read = read;
	}
	void setFix(bool fix){
		this -> fix = fix;	
	}

	//****** Team members can not set priority level, they can only view. 

};

int main(){

	return 0;
}