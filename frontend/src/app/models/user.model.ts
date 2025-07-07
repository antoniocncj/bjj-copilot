export interface User {
  id: string;
  name: string;
  email: string;
  role: 'STUDENT' | 'INSTRUCTOR';
  profile?: UserProfile;
  active: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface UserProfile {
  dateOfBirth?: string;
  phoneNumber?: string;
  emergencyContact?: string;
  currentBelt?: Belt;
  startDate?: string;
}

export interface Belt {
  id: string;
  name: string;
  colorCode: string;
  displayName: string;
  sortOrder: number;
}

export interface CreateUserRequest {
  name: string;
  email: string;
  role: 'STUDENT' | 'INSTRUCTOR';
  dateOfBirth?: string;
  phoneNumber?: string;
  emergencyContact?: string;
}